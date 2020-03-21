package org.d3if4127.jurnal07

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import org.d3if4127.jurnal07.databinding.FragmentDashboardBinding

/**
 * A simple [Fragment] subclass.
 */
class dashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var diaryVM: DiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).DiaryDao
        val viewModelFactory = DiaryViewModelFactory(dataSource, application)
        diaryVM = ViewModelProviders.of(this, viewModelFactory).get(DiaryViewModel::class.java)
        binding.diaryVM = diaryVM
        binding.setLifecycleOwner(this)

        diaryVM.diarys.observe(viewLifecycleOwner, Observer { hasil ->
            hasil.forEach {
                Log.i("hasill", "${it.diary}")
            }
        })

        binding.toWriteDiary.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_dashboardFragment_to_writeFragment)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow,menu)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.clearData -> clearData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun clearData() {
        diaryVM.onClear()
    }

}
