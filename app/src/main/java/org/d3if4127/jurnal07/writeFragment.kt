package org.d3if4127.jurnal07

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import org.d3if4127.jurnal07.databinding.FragmentWriteBinding

/**
 * A simple [Fragment] subclass.
 */
class writeFragment : Fragment() {

    private lateinit var binding: FragmentWriteBinding
    private lateinit var diaryVM: DiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_write, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).DiaryDao
        val viewModelFactory = DiaryViewModelFactory(dataSource, application)
        diaryVM = ViewModelProviders.of(this, viewModelFactory).get(DiaryViewModel::class.java)
        binding.setLifecycleOwner(this)

        binding.addItem.setOnClickListener {
            try {
                var input = binding.inputDiary.text.toString()
                addDiary(input)
            }catch (e: Exception) {

            }
        }

        return binding.root
    }

    fun addDiary(input: String) {
        diaryVM.onClickTambah(input)
    }

}
