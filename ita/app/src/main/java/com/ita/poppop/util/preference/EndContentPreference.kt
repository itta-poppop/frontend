package com.ita.poppop.util.preference

import android.content.Context
import android.util.AttributeSet
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.ita.poppop.R
import com.ita.poppop.databinding.PreferenceWithEndContentBinding
import com.ita.poppop.databinding.PreferenceWithEndIconBinding


class EndContentPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.preference.R.attr.preferenceStyle
) : Preference(context, attrs, defStyleAttr) {

    init {
        layoutResource = R.layout.preference_with_end_content
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)

        // ViewBinding을 수동으로 바인딩 (Preference는 자동 바인딩 불가)
        val itemView = holder.itemView
        val binding = PreferenceWithEndContentBinding.bind(itemView)

        binding.tvTitle.text = title
        binding.tvContent.text = summary
    }
}