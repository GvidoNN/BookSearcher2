package com.example.booksearcher2.domain.usecase

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.booksearcher2.domain.models.api.DataResponse

class SpeechRecognizerUseCase: RecognitionListener {

    val text = MutableLiveData<String>()

    override fun onReadyForSpeech(params: Bundle?) {
        Log.d("MyLog","Готов")
    }

    override fun onBeginningOfSpeech() {
        Log.d("MyLog","Начал")
    }

    override fun onRmsChanged(p0: Float) {

    }

    override fun onBufferReceived(buffer: ByteArray?) {
        Log.d("MyLog","аудио(хз)")
    }

    override fun onEndOfSpeech() {
        Log.d("MyLog","Закончил")

    }

    override fun onError(error: Int) {
        // Called when an error occurs during recognition
        Log.d("MyLog","Ошибка")
    }

    override fun onResults(results: Bundle?) {
        // Called when the recognizer has results to return
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        //binding.edEnter.setText(matches?.get(0))
        //Toast.makeText(requireContext(), "You said: ${matches?.get(0)}", Toast.LENGTH_SHORT).show()
        Log.d("MyLog","You said: ${matches?.get(0)}")
        text.value = matches?.get(0)
    }

    override fun onPartialResults(p0: Bundle?) {

    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        Log.d("MyLog","Интернет пропал")
    }
}