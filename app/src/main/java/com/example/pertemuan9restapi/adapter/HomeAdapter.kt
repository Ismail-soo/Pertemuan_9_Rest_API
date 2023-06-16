package com.example.pertemuan9restapi.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan9restapi.R
import com.example.pertemuan9restapi.databinding.UserListBinding
import com.example.pertemuan9restapi.model.request.DataAllMahasiswa
import com.example.pertemuan9restapi.model.response.ResponseDataMahasiswa
import com.example.pertemuan9restapi.model.response.ResponseDeleteMahasiswa
import com.example.pertemuan9restapi.network.ApiClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class HomeAdapter(private var dataMhs : List<DataAllMahasiswa>)
    : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){
    class ViewHolder(val binding : UserListBinding) : RecyclerView.ViewHolder(binding.root){
        var api = ApiClient.instance
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = UserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return dataMhs.size
    }
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position:Int) {
        holder.binding.txtNama.text = dataMhs[position].nama
        holder.binding.txtNim.text = dataMhs[position].nIM
        holder.binding.txtTelepon.text = dataMhs[position].telepon
        holder.binding.cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("nim", dataMhs[position].nIM)
            bundle.putString("nama", dataMhs[position].nama)
            bundle.putString("telepon", dataMhs[position].telepon)
            Navigation.findNavController(it).navigate(com.example.pertemuan9restapi.R.id.homeFragment, bundle)
        }

        holder.binding.btnEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("nim", dataMhs[position].nIM)
            bundle.putString("nama", dataMhs[position].nama)
            bundle.putString("telepon", dataMhs[position].telepon)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_updateFragment, bundle)
        }

        holder.binding.btnDelete.setOnClickListener {
            holder.api.deleteDataMahasiswa(dataMhs[position].nIM).enqueue(object : retrofit2.Callback<ResponseDeleteMahasiswa> {
                override fun onResponse(
                    call: Call<ResponseDeleteMahasiswa>,
                    response: Response<ResponseDeleteMahasiswa>
                ) {
                    if (response.isSuccessful){
                        dataMhs = dataMhs.toMutableList().apply {
                            removeAt(position)
                        }
                        notifyDataSetChanged()
                        Toast.makeText(holder.itemView.context,"Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseDeleteMahasiswa>, t: Throwable) {
                    Toast.makeText(holder.itemView.context,"Data gagal dihapus",Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}
