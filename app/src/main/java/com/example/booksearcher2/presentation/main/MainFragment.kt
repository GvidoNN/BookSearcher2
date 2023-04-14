package com.example.booksearcher2.presentation.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearcher2.R
import com.example.booksearcher2.databinding.FragmentMainBinding
import com.example.booksearcher2.domain.usecase.SpeechRecognizerUseCase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main){

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: SearchInsideAdapter
    private lateinit var errorContainer: LinearLayout
    private lateinit var btErrorTryAgain: Button
    private lateinit var binding: FragmentMainBinding
    private lateinit var speechRecognizer: SpeechRecognizer
//    private val SpeechRecognizerUseCase by lazy {SpeechRecognizerUseCase()}

    override fun onCreate(savedInstanceState: Bundle?) {
        adapter = SearchInsideAdapter()
        super.onCreate(savedInstanceState)
        Log.d("MyLog","OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyLog","OnViewCreated")

        errorContainer = requireView().findViewById(R.id.errorContainer)
        btErrorTryAgain = requireView().findViewById(R.id.btErrorTryAgain)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mainViewModel.searchInside.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                adapter.setMovieList(result.hits.hits)
                errorContainer.isVisible = false
                binding.recyclerView.isVisible = true
            } else {
                errorContainer.isVisible = true
                binding.recyclerView.isVisible = false
            }
        }

        binding.btSearch.setOnClickListener {
            setOnClick()
        }

        btErrorTryAgain.setOnClickListener {
            setOnClick()
        }

        adapter.setOnItemClickListener(object: SearchInsideAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                saveBookData(position = position)
            }
        })

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        speechRecognizer.setRecognitionListener(mainViewModel.giveSpeechInterface())

        binding.btMicro.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            speechRecognizer.startListening(intent)
            mainViewModel.giveText().observe(viewLifecycleOwner){
                binding.edEnter.setText(it)
                Log.d("MyLog","Сейчас сказал: ${it}")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }

    fun setOnClick() {
        var text = binding.edEnter.text.toString()
        mainViewModel.searchResponce(text)

        mainViewModel.progressBar.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.progressBar.isVisible = true
                binding.recyclerView.isVisible = false
                binding.btSearch.isClickable = false
            } else {
                binding.progressBar.isVisible = false
                binding.btSearch.isClickable = true
            }
        }
    }

    private fun saveBookData(position: Int) {
        mainViewModel.insertBook(
            mainViewModel.checkBook(position = position, adapter = adapter)
        )
    }
}