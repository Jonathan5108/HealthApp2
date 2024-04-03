package example.abhiandroid.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

class ListViewAdapter(private val mContext: Context, private var animalNamesList: List<AnimalNames>) :
    BaseAdapter() {

    private var inflater: LayoutInflater = LayoutInflater.from(mContext)
    private var arraylist: ArrayList<AnimalNames> = ArrayList()

    init {
        arraylist.addAll(animalNamesList)
    }

    inner class ViewHolder {
        var name: TextView? = null
    }

    override fun getCount(): Int {
        return animalNamesList.size
    }

    override fun getItem(position: Int): AnimalNames {
        return animalNamesList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        val holder: ViewHolder
        if (convertView == null) {
            holder = ViewHolder()
            convertView = inflater.inflate(R.layout.listview_item, null)
            // Locate the TextViews in listview_item.xml
            holder.name = convertView.findViewById<View>(R.id.name) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        // Set the results into TextViews
        holder.name?.text = animalNamesList[position].animalName
        return convertView!!
    }

    // Filter Class
    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        animalNamesList = if (charText.isEmpty()) {
            arraylist
        } else {
            val filteredList = ArrayList<AnimalNames>()
            for (wp in arraylist) {
                if (wp.animalName.toLowerCase(Locale.getDefault()).contains(charText)) {
                    filteredList.add(wp)
                }
            }
            filteredList
        }
        notifyDataSetChanged()
    }

}

// derived from : https://abhiandroid.com/ui/searchview#gsc.tab=0