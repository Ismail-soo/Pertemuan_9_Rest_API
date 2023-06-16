import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pertemuan9restapi.model.request.DataAllMahasiswa
import com.example.pertemuan9restapi.model.request.Mahasiswa
import com.example.pertemuan9restapi.model.response.*
import com.example.pertemuan9restapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMahasiswa : ViewModel(){
    private val getDataMahasiswa = MutableLiveData<List<DataAllMahasiswa>?>()
    private val detailMahasiswa = MutableLiveData<ResponseDetailDataMahasiswa?>()
    private val insertMahasiswa = MutableLiveData<ResponseAddMahasiswa?>()
    private val updateMahasiswa = MutableLiveData<ResponseUpdateMahasiswa?>()
    fun getDataMahasiswa() : MutableLiveData<List<DataAllMahasiswa>?>{
        return getDataMahasiswa
    }
    fun getDetailMahasiswa(): MutableLiveData<ResponseDetailDataMahasiswa?> {
        return detailMahasiswa
    }
    fun insertMahasiswa(): MutableLiveData<ResponseAddMahasiswa?> { return insertMahasiswa
    }
    fun updateMahasiswa(): MutableLiveData<ResponseUpdateMahasiswa?> {
        return updateMahasiswa
    }

    fun showDataMahasiswa(){
        ApiClient.instance.getDataMahasiswa().enqueue(object : Callback<ResponseDataMahasiswa>{
            override fun onResponse(call: Call<ResponseDataMahasiswa>, response: Response<ResponseDataMahasiswa>)
            {
                if (response.isSuccessful){
                    getDataMahasiswa.postValue(response.body()?.data)
                }else{
                    getDataMahasiswa.postValue(null)
                }
            }
            override fun onFailure(call:
                                   Call<ResponseDataMahasiswa>,t: Throwable){
                getDataMahasiswa.postValue(null)
            }
        })
    }
    fun getDetailData(nim: String) {
        ApiClient.instance.getDetailMahasiswa(nim).enqueue(object
            : Callback<ResponseDetailDataMahasiswa> {
            override fun onResponse(
                call: Call<ResponseDetailDataMahasiswa>,
                response: Response<ResponseDetailDataMahasiswa>
            ) {
                if (response.isSuccessful) {
                    detailMahasiswa.postValue(response.body())
                } else {
                    detailMahasiswa.postValue(null)
                }
            }
            override fun onFailure(call:
                                   Call<ResponseDetailDataMahasiswa>, t: Throwable) {detailMahasiswa.postValue(null)
            }
        })
    }

    fun insertMahasiswa(nim : String, nama :String,telepon : String){
        ApiClient.instance.addDataMahasiswa(Mahasiswa(nim,nama,telepon)).enqueue(object : Callback<ResponseAddMahasiswa>{
            override fun onResponse(call: Call<ResponseAddMahasiswa>, response: Response<ResponseAddMahasiswa>) {
                    if (response.isSuccessful){
                        insertMahasiswa.postValue(response.body())
                    }else{
                        insertMahasiswa.postValue(null)
                    }
                }
                override fun onFailure(call: Call<ResponseAddMahasiswa>, t: Throwable) {
                    insertMahasiswa.postValue(null)
                }
            })
    }
    fun updateMahasiswa(nim : String, nama :String,telepon : String){
        ApiClient.instance.updateDataMahasiswa(nim, Mahasiswa(nim,nama,telepon)).enqueue(object : Callback<ResponseUpdateMahasiswa>{
            override fun onResponse(call: Call<ResponseUpdateMahasiswa>, response: Response<ResponseUpdateMahasiswa>) {
                if (response.isSuccessful){
                    updateMahasiswa.postValue(response.body())
                }else{
                    updateMahasiswa.postValue(null)
                }
            }
            override fun onFailure(call: Call<ResponseUpdateMahasiswa>, t: Throwable) {
                updateMahasiswa.postValue(null)
            }
        })
    }


}



















