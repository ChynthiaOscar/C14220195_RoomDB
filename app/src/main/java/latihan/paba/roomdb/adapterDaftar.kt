package latihan.paba.roomdb

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import latihan.paba.roomdb.database.daftarBelanja

class adapterDaftar (private val daftarBelanja : MutableList<daftarBelanja>) : RecyclerView.Adapter<adapterDaftar.ListViewHolder>() {

    private lateinit var onItemClickCallback : OnItemCLickCallback
    interface OnItemCLickCallback {
        fun delData(dtBelanja: daftarBelanja)
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemCLickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterDaftar.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recview, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return daftarBelanja.size
    }

    override fun onBindViewHolder(holder: adapterDaftar.ListViewHolder, position: Int) {
        var daftar = daftarBelanja[position]

        holder.tvTanggal.setText(daftar.tanggal)
        holder.tvItem.setText(daftar.item)
        holder.tvJumlah.setText(daftar.jumlah)

        holder.btnEdit.setOnClickListener {
            val intent = Intent(it.context, TambahDaftar::class.java)
            intent.putExtra("id", daftar.id)
            intent.putExtra("addEdit", 1)
            it.context.startActivity(intent)
        }

        holder.btnHapus.setOnClickListener {
            onItemClickCallback.delData(daftar)
        }
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTanggal = itemView.findViewById<TextView>(R.id.tvTanggal)
        var tvItem = itemView.findViewById<TextView>(R.id.tvItem)
        var tvJumlah = itemView.findViewById<TextView>(R.id.tvJumlah)

        var btnHapus = itemView.findViewById<ImageButton>(R.id.btnHapus)
        var btnEdit = itemView.findViewById<ImageButton>(R.id.btnEdit)
    }
}