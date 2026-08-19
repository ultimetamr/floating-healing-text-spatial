param(
    [Parameter(Mandatory = $true)]
    [string]$BackgroundInput,
    [Parameter(Mandatory = $true)]
    [string]$ProjectRoot
)

$ErrorActionPreference = "Stop"
Add-Type -AssemblyName System.Drawing

$drawingAssemblies = @(
    [System.Drawing.Bitmap].Assembly.Location,
    [System.Drawing.Rectangle].Assembly.Location,
    (Join-Path $PSHOME "System.Private.Windows.GdiPlus.dll"),
    (Join-Path $PSHOME "System.Private.Windows.Core.dll")
)
Add-Type -ReferencedAssemblies $drawingAssemblies -TypeDefinition @'
using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.Runtime.InteropServices;

public static class PicoIconRaster {
    private static int[] ReadPixels(Bitmap bitmap) {
        var rect = new Rectangle(0, 0, bitmap.Width, bitmap.Height);
        var data = bitmap.LockBits(rect, ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
        try {
            var pixels = new int[bitmap.Width * bitmap.Height];
            Marshal.Copy(data.Scan0, pixels, 0, pixels.Length);
            return pixels;
        } finally {
            bitmap.UnlockBits(data);
        }
    }

    private static void WritePixels(Bitmap bitmap, int[] pixels) {
        var rect = new Rectangle(0, 0, bitmap.Width, bitmap.Height);
        var data = bitmap.LockBits(rect, ImageLockMode.WriteOnly, PixelFormat.Format32bppArgb);
        try {
            Marshal.Copy(pixels, 0, data.Scan0, pixels.Length);
        } finally {
            bitmap.UnlockBits(data);
        }
    }

    public static void SnapAlpha(string inputPath, string outputPath) {
        using (var source = new Bitmap(inputPath))
        using (var output = new Bitmap(source.Width, source.Height, PixelFormat.Format32bppArgb)) {
            var pixels = ReadPixels(source);
            for (int i = 0; i < pixels.Length; i++) {
                int alpha = (pixels[i] >> 24) & 255;
                pixels[i] = alpha >= 64 ? (pixels[i] | unchecked((int)0xFF000000)) : 0;
            }
            WritePixels(output, pixels);
            output.Save(outputPath, ImageFormat.Png);
        }
    }

    private static float[] DistanceTo(bool[] feature, int width, int height) {
        const float inf = 1000000f;
        const float diagonal = 1.41421356f;
        var distance = new float[feature.Length];
        for (int i = 0; i < feature.Length; i++) distance[i] = feature[i] ? 0f : inf;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int i = y * width + x;
                float best = distance[i];
                if (x > 0) best = Math.Min(best, distance[i - 1] + 1f);
                if (y > 0) best = Math.Min(best, distance[i - width] + 1f);
                if (x > 0 && y > 0) best = Math.Min(best, distance[i - width - 1] + diagonal);
                if (x + 1 < width && y > 0) best = Math.Min(best, distance[i - width + 1] + diagonal);
                distance[i] = best;
            }
        }

        for (int y = height - 1; y >= 0; y--) {
            for (int x = width - 1; x >= 0; x--) {
                int i = y * width + x;
                float best = distance[i];
                if (x + 1 < width) best = Math.Min(best, distance[i + 1] + 1f);
                if (y + 1 < height) best = Math.Min(best, distance[i + width] + 1f);
                if (x + 1 < width && y + 1 < height) best = Math.Min(best, distance[i + width + 1] + diagonal);
                if (x > 0 && y + 1 < height) best = Math.Min(best, distance[i + width - 1] + diagonal);
                distance[i] = best;
            }
        }
        return distance;
    }

    public static void GenerateSdf(string maskPath, string outputPath) {
        using (var source = new Bitmap(maskPath))
        using (var output = new Bitmap(source.Width, source.Height, PixelFormat.Format32bppArgb)) {
            int width = source.Width;
            int height = source.Height;
            var pixels = ReadPixels(source);
            var inside = new bool[pixels.Length];
            var outside = new bool[pixels.Length];
            for (int i = 0; i < pixels.Length; i++) {
                inside[i] = ((pixels[i] >> 24) & 255) >= 128;
                outside[i] = !inside[i];
            }

            var toInside = DistanceTo(inside, width, height);
            var toOutside = DistanceTo(outside, width, height);
            var sdf = new int[pixels.Length];
            const float range = 28f;
            for (int i = 0; i < sdf.Length; i++) {
                float signedDistance = inside[i] ? toOutside[i] : -toInside[i];
                int value = (int)Math.Round(128f + signedDistance * 127f / range);
                value = Math.Max(0, Math.Min(255, value));
                sdf[i] = unchecked((int)0xFF000000) | (value << 16) | (value << 8) | value;
            }
            WritePixels(output, sdf);
            output.Save(outputPath, ImageFormat.Png);
        }
    }
}
'@

function New-RoundedPath {
    param([float]$X, [float]$Y, [float]$Width, [float]$Height, [float]$Radius)
    $path = [System.Drawing.Drawing2D.GraphicsPath]::new()
    $diameter = $Radius * 2
    $path.AddArc($X, $Y, $diameter, $diameter, 180, 90)
    $path.AddArc($X + $Width - $diameter, $Y, $diameter, $diameter, 270, 90)
    $path.AddArc($X + $Width - $diameter, $Y + $Height - $diameter, $diameter, $diameter, 0, 90)
    $path.AddArc($X, $Y + $Height - $diameter, $diameter, $diameter, 90, 90)
    $path.CloseFigure()
    return $path
}

function Draw-PhraseRibbon {
    param(
        [System.Drawing.Graphics]$Graphics,
        [float]$X,
        [float]$Y,
        [System.Drawing.Color]$StartColor,
        [System.Drawing.Color]$EndColor
    )
    $width = 560.0
    $height = 142.0
    $path = New-RoundedPath -X $X -Y $Y -Width $width -Height $height -Radius 58
    $brush = [System.Drawing.Drawing2D.LinearGradientBrush]::new(
        [System.Drawing.PointF]::new($X, $Y),
        [System.Drawing.PointF]::new($X + $width, $Y + $height),
        $StartColor,
        $EndColor
    )
    $outline = [System.Drawing.Pen]::new([System.Drawing.Color]::FromArgb(255, 248, 250, 255), 5)
    $Graphics.FillPath($brush, $path)
    $Graphics.DrawPath($outline, $path)

    $bulletBrush = [System.Drawing.SolidBrush]::new([System.Drawing.Color]::FromArgb(255, 255, 250, 235))
    $Graphics.FillEllipse($bulletBrush, $X + 45, $Y + 39, 64, 64)
    $linePen = [System.Drawing.Pen]::new([System.Drawing.Color]::FromArgb(255, 255, 250, 242), 22)
    $linePen.StartCap = [System.Drawing.Drawing2D.LineCap]::Round
    $linePen.EndCap = [System.Drawing.Drawing2D.LineCap]::Round
    $Graphics.DrawLine($linePen, $X + 150, $Y + 53, $X + 465, $Y + 53)
    $Graphics.DrawLine($linePen, $X + 150, $Y + 91, $X + 390, $Y + 91)

    $linePen.Dispose()
    $bulletBrush.Dispose()
    $outline.Dispose()
    $brush.Dispose()
    $path.Dispose()
}

$drawableDir = Join-Path $ProjectRoot "app\src\main\res\drawable"
$mipmapDir = Join-Path $ProjectRoot "app\src\main\res\mipmap-xxxhdpi"
New-Item -ItemType Directory -Force -Path $drawableDir, $mipmapDir | Out-Null

$backgroundPath = Join-Path $drawableDir "icon_healing_bg.png"
$foregroundPath = Join-Path $drawableDir "icon_healing_fg.png"
$backgroundSdfPath = Join-Path $drawableDir "icon_healing_sdf_bg.png"
$foregroundSdfPath = Join-Path $drawableDir "icon_healing_sdf_fg.png"
$launcherPath = Join-Path $mipmapDir "ic_healing_space_launcher_v2.png"
$rawForegroundPath = Join-Path $drawableDir ".icon_healing_fg_raw.png"
$backgroundMaskPath = Join-Path $drawableDir ".icon_healing_bg_mask.png"

$source = [System.Drawing.Bitmap]::FromFile((Resolve-Path $BackgroundInput))
$background = [System.Drawing.Bitmap]::new(1024, 1024, [System.Drawing.Imaging.PixelFormat]::Format32bppArgb)
$graphics = [System.Drawing.Graphics]::FromImage($background)
$graphics.CompositingQuality = [System.Drawing.Drawing2D.CompositingQuality]::HighQuality
$graphics.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::HighQualityBicubic
$graphics.PixelOffsetMode = [System.Drawing.Drawing2D.PixelOffsetMode]::HighQuality
$graphics.Clear([System.Drawing.Color]::Transparent)
$backgroundClip = New-RoundedPath -X 0 -Y 0 -Width 1024 -Height 1024 -Radius 180
$graphics.SetClip($backgroundClip)
$graphics.DrawImage($source, [System.Drawing.Rectangle]::new(0, 0, 1024, 1024), [System.Drawing.Rectangle]::new(30, 30, $source.Width - 60, $source.Height - 60), [System.Drawing.GraphicsUnit]::Pixel)
$graphics.ResetClip()
$backgroundClip.Dispose()
$graphics.Dispose()
$source.Dispose()
$background.Save($backgroundPath, [System.Drawing.Imaging.ImageFormat]::Png)

$foreground = [System.Drawing.Bitmap]::new(1024, 1024, [System.Drawing.Imaging.PixelFormat]::Format32bppArgb)
$graphics = [System.Drawing.Graphics]::FromImage($foreground)
$graphics.Clear([System.Drawing.Color]::Transparent)
$graphics.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
$graphics.CompositingQuality = [System.Drawing.Drawing2D.CompositingQuality]::HighQuality
Draw-PhraseRibbon -Graphics $graphics -X 214 -Y 170 -StartColor ([System.Drawing.ColorTranslator]::FromHtml("#FFE4B5")) -EndColor ([System.Drawing.ColorTranslator]::FromHtml("#EFCBFF"))
Draw-PhraseRibbon -Graphics $graphics -X 304 -Y 418 -StartColor ([System.Drawing.ColorTranslator]::FromHtml("#FFB6C1")) -EndColor ([System.Drawing.ColorTranslator]::FromHtml("#C8B5FF"))
Draw-PhraseRibbon -Graphics $graphics -X 220 -Y 666 -StartColor ([System.Drawing.ColorTranslator]::FromHtml("#B0E0E6")) -EndColor ([System.Drawing.ColorTranslator]::FromHtml("#78C7FF"))

$star = [System.Drawing.Drawing2D.GraphicsPath]::new()
$centerX = 844.0
$centerY = 166.0
for ($i = 0; $i -lt 16; $i++) {
    $angle = (-90 + $i * 22.5) * [Math]::PI / 180
    $radius = if (($i % 2) -eq 0) { 62.0 } else { 17.0 }
    $point = [System.Drawing.PointF]::new(
        [float]($centerX + [Math]::Cos($angle) * $radius),
        [float]($centerY + [Math]::Sin($angle) * $radius)
    )
    if ($i -eq 0) { $star.StartFigure(); $star.AddLine($point, $point) } else { $star.AddLine($star.GetLastPoint(), $point) }
}
$star.CloseFigure()
$starBrush = [System.Drawing.SolidBrush]::new([System.Drawing.Color]::FromArgb(255, 255, 229, 154))
$graphics.FillPath($starBrush, $star)
$starBrush.Dispose()
$star.Dispose()
$graphics.Dispose()
$foreground.Save($rawForegroundPath, [System.Drawing.Imaging.ImageFormat]::Png)
$foreground.Dispose()
[PicoIconRaster]::SnapAlpha($rawForegroundPath, $foregroundPath)

$launcher = [System.Drawing.Bitmap]::new(1024, 1024, [System.Drawing.Imaging.PixelFormat]::Format32bppArgb)
$graphics = [System.Drawing.Graphics]::FromImage($launcher)
$graphics.DrawImageUnscaled($background, 0, 0)
$foregroundForComposite = [System.Drawing.Bitmap]::FromFile($foregroundPath)
$graphics.DrawImageUnscaled($foregroundForComposite, 0, 0)
$foregroundForComposite.Dispose()
$graphics.Dispose()
$launcher.Save($launcherPath, [System.Drawing.Imaging.ImageFormat]::Png)
$launcher.Dispose()
$background.Dispose()

$backgroundMask = [System.Drawing.Bitmap]::new(1024, 1024, [System.Drawing.Imaging.PixelFormat]::Format32bppArgb)
$graphics = [System.Drawing.Graphics]::FromImage($backgroundMask)
$graphics.Clear([System.Drawing.Color]::Transparent)
$graphics.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
$maskPath = New-RoundedPath -X 0 -Y 0 -Width 1024 -Height 1024 -Radius 180
$maskBrush = [System.Drawing.SolidBrush]::new([System.Drawing.Color]::White)
$graphics.FillPath($maskBrush, $maskPath)
$maskBrush.Dispose()
$maskPath.Dispose()
$graphics.Dispose()
$backgroundMask.Save($backgroundMaskPath, [System.Drawing.Imaging.ImageFormat]::Png)
$backgroundMask.Dispose()

[PicoIconRaster]::GenerateSdf($backgroundMaskPath, $backgroundSdfPath)
[PicoIconRaster]::GenerateSdf($foregroundPath, $foregroundSdfPath)

Remove-Item -LiteralPath $rawForegroundPath, $backgroundMaskPath

Write-Output "Generated PICO layered icon assets:"
Get-Item $backgroundPath, $foregroundPath, $backgroundSdfPath, $foregroundSdfPath, $launcherPath |
    Select-Object FullName, Length
