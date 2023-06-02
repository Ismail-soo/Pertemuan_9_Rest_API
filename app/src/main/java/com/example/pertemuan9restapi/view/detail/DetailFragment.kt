package com.example.pertemuan9restapi.view.detail

import ViewModelMahasiswa
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pertemuan9restapi.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    lateinit var viewModel : ViewModelMahasiswa
    lateinit var binding : FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        binding =
            FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }override fun onViewCreated(view: View, savedInstanceState:
    Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nim = arguments?.getString("nim")
        viewModel =
            ViewModelProvider(requireActivity()).get(ViewModelMahasiswa::class.java)
        viewModel.getDetailMahasiswa().observe(viewLifecycleOwner){
            if (it != null){
                binding.txtnim.text = it.data?.nim
                binding.txtnama.text = it.data?.nama
                binding.txtTelepon.text = it.data?.telepon
            }else{
                Toast.makeText(context,"Data tidak ditemukan",Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getDetailData(nim!!)
    }
}