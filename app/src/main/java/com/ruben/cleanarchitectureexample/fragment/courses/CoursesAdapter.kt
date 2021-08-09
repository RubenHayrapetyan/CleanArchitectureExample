package com.ruben.cleanarchitectureexample.fragment.courses

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.ruben.cleanarchitectureexample.R
import com.ruben.cleanarchitectureexample.databinding.ItemCourseBinding
import com.ruben.entities.localmodels.courses.CoursesItemLocal
import com.ruben.cleanarchitectureexample.base.adapter.BaseAdapter
import com.ruben.cleanarchitectureexample.base.adapter.BaseViewHolder

class CoursesAdapter:
    BaseAdapter<ViewBinding, CoursesItemLocal, BaseViewHolder<CoursesItemLocal, ViewBinding>>(
        EventsDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CoursesItemLocal, ViewBinding> {
        return ItemViewHolder(
            ItemCourseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ItemViewHolder(private val binding: ItemCourseBinding) :
        BaseViewHolder<CoursesItemLocal, ViewBinding>(binding) {
        override fun bind(item: CoursesItemLocal) {
            with(binding) {
                courseHeaderTxt.text = item.courseName
                itemView.context?.let {
                    Glide.with(it)
                        .load(item.iconUrl)
                        .circleCrop()
                        .placeholder(ContextCompat.getDrawable(it, R.drawable.ic_launcher_foreground))
                        .into(courseLogoImg)
                }
            }
        }

    }
}

internal class EventsDiffCallback : DiffUtil.ItemCallback<CoursesItemLocal>() {
    override fun areItemsTheSame(oldItem: CoursesItemLocal, newItem: CoursesItemLocal): Boolean =
        oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: CoursesItemLocal, newItem: CoursesItemLocal): Boolean =
        oldItem == newItem
}