package com.example.vymoassignment.binding

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vymoassignment.R
import com.example.vymoassignment.utils.DATE_FORMAT_PR
import java.text.SimpleDateFormat
import java.util.*

class CustomBinding {
    companion object {

        /**
         * BindingAdapter for handling error
         * */
        @BindingAdapter("error")
        @JvmStatic
        fun setEditError(editText: EditText, strOrResId: Any?) {
            if (strOrResId is Int) {
                editText.error = editText.context.getString((strOrResId as Int?)!!)
            } else {
                editText.error = strOrResId as String?
            }
            editText.requestFocus()
        }

        @JvmStatic
        @BindingAdapter("setAdapter")
        fun bindRecyclerViewAdapter(
            recyclerView: RecyclerView,
            adapter: RecyclerView.Adapter<*>?
        ) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapter
        }

        @JvmStatic
        @BindingAdapter("statusText")
        fun handleStatus(textView: TextView, text:String?) {
            if(textView.context!=null) {
                textView.text = text
                if(text == "open")
                    textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.green_circle, 0, 0, 0);
                else
                    textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.red_circle, 0, 0, 0);

            }
        }

        @JvmStatic
        @BindingAdapter("createdTxt")
        fun created(textView: TextView, text:String?) {
            if(textView.context!=null) {
                textView.text = getBlackListedDate(text)
            }
        }

        /**
         * convert date for blacklist
         * */
        private fun getBlackListedDate(time: String?): String? {
            var date =  Date()
            time?.let{
                var format = SimpleDateFormat(DATE_FORMAT_PR)
                date =  format.parse(time)
            }
            return getDisplayableTime(date.time);
        }

        private fun getDisplayableTime(delta: Long): String? {
            var difference: Long = 0
            val mDate = System.currentTimeMillis()
            if (mDate > delta) {
                difference = mDate - delta
                val seconds = difference / 1000
                val minutes = seconds / 60
                val hours = minutes / 60
                val days = hours / 24
                val months = days / 31
                val years = days / 365
                return if (seconds < 0) {
                    "not yet"
                } else if (seconds < 60) {
                    if (seconds == 1L) "one second ago" else "$seconds seconds ago"
                } else if (seconds < 120) {
                    "a minute ago"
                } else if (seconds < 2700) // 45 * 60
                {
                    "$minutes minutes ago"
                } else if (seconds < 5400) // 90 * 60
                {
                    "an hour ago"
                } else if (seconds < 86400) // 24 * 60 * 60
                {
                    "$hours hours ago"
                } else if (seconds < 172800) // 48 * 60 * 60
                {
                    "yesterday"
                } else if (seconds < 2592000) // 30 * 24 * 60 * 60
                {
                    "$days days ago"
                } else if (seconds < 31104000) // 12 * 30 * 24 * 60 * 60
                {
                    if (months <= 1) "one month ago" else "$days months ago"
                } else {
                    if (years <= 1) "one year ago" else "$years years ago"
                }
            }
            return null
        }
    }



}