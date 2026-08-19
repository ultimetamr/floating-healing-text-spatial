package com.spatialapps.floatinghealingtext.voice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer

class SpeechInputController(
    context: Context,
    private val onStatus: (String, String?) -> Unit,
    private val onResult: (String) -> Unit,
) : RecognitionListener {
    private val recognizer = SpeechRecognizer.createSpeechRecognizer(context.applicationContext).also {
        it.setRecognitionListener(this)
    }

    fun start() {
        if (!SpeechRecognizer.isRecognitionAvailable(recognizerContext)) {
            onStatus("当前环境不支持语音识别", "请使用空间键盘输入")
            return
        }
        onStatus("正在聆听…", null)
        recognizer.startListening(
            Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "zh-CN")
                putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
                putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
            },
        )
    }

    fun stop() = recognizer.stopListening()

    fun destroy() = recognizer.destroy()

    private val recognizerContext: Context = context.applicationContext

    override fun onReadyForSpeech(params: Bundle?) = onStatus("请说出想留给自己的话", null)
    override fun onBeginningOfSpeech() = onStatus("正在记录你的话…", null)
    override fun onRmsChanged(rmsdB: Float) = Unit
    override fun onBufferReceived(buffer: ByteArray?) = Unit
    override fun onEndOfSpeech() = onStatus("正在整理文字…", null)
    override fun onEvent(eventType: Int, params: Bundle?) = Unit

    override fun onError(error: Int) {
        onStatus("语音未完成", "识别错误 $error · 可继续使用键盘")
    }

    override fun onResults(results: Bundle?) {
        results?.bestResult()?.let(onResult)
            ?: onStatus("没有听清", "请重试或使用键盘")
    }

    override fun onPartialResults(partialResults: Bundle?) {
        partialResults?.bestResult()?.let(onResult)
    }

    private fun Bundle.bestResult(): String? =
        getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            ?.firstOrNull()
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
}
