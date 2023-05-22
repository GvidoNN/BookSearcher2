package my.lovely.booksearcher2.domain.usecase

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import androidx.lifecycle.MutableLiveData

class SpeechRecognizerUseCase: RecognitionListener {

    val text = MutableLiveData<String>()
    val statusOfSpeaking = MutableLiveData<Boolean>()

    override fun onReadyForSpeech(params: Bundle?) {
    }

    override fun onBeginningOfSpeech() {
        statusOfSpeaking.value = true
    }

    override fun onRmsChanged(p0: Float) {

    }

    override fun onBufferReceived(buffer: ByteArray?) {
    }

    override fun onEndOfSpeech() {
    }

    override fun onError(error: Int) {
        // Called when an error occurs during recognition
        statusOfSpeaking.value = false
    }

    override fun onResults(results: Bundle?) {
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        text.value = matches?.get(0)
        statusOfSpeaking.value = false
    }

    override fun onPartialResults(p0: Bundle?) {
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
    }
}