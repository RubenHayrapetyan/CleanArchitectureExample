package com.ruben.cleanarchitectureexample.fragment.courses

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ruben.cleanarchitectureexample.base.FragmentBaseMVVM
import com.ruben.cleanarchitectureexample.base.utils.viewBinding
import com.ruben.cleanarchitectureexample.databinding.FragmentCoursesBinding
import com.ruben.cleanarchitectureexample.utils.gone
import com.ruben.cleanarchitectureexample.utils.hasNetwork
import com.ruben.cleanarchitectureexample.utils.visible
import com.ruben.cleanarchitectureexample.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoursesFragment : FragmentBaseMVVM<CoursesViewModel, FragmentCoursesBinding>() {

    override val viewModel  by viewModel<CoursesViewModel>()
    override val binding: FragmentCoursesBinding by viewBinding()
    private var coursesAdapter = CoursesAdapter()
    var isLoading = false
    override fun initView() {
        with(binding) {
            coursesRecycler.apply {
                context?.let {
                    layoutManager =
                        LinearLayoutManager(it)
                    setHasFixedSize(true)
                    adapter = coursesAdapter
                }
            }
            coursesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    if (!isLoading) {
                        if (linearLayoutManager != null &&
                            linearLayoutManager.findLastCompletelyVisibleItemPosition() ==
                            viewModel.getCoursesItem.value?.size?.minus(
                                1
                            )
                        ) {
                            isLoading = true
                        }
                    }
                }
            })
        }
        loadData()
    }
    private fun loadData(){
        context?.let {
            if (it.hasNetwork()) {
                    viewModel.getAllCourses()
            } else {
                viewModel.loadDataDb()
                Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun observes() {
        observe(viewModel.getCoursesItem) {
            coursesAdapter.submitList(it)
            isLoading = false
        }
        observe(viewModel.errorNullData) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        observe(viewModel.notMoreItem) {
            Toast.makeText(context, "Load more data is empty", Toast.LENGTH_SHORT).show()
            isLoading = false
        }
        observe(viewModel.dbDataIsEmpty) {
            if (it) binding.emptyData.visible()
            else binding.emptyData.gone()

        }
        observe(viewModel.loadingData) {
            if (it) binding.loadingPb.visible()
            else binding.loadingPb.gone()
        }
    }

    override fun navigateUp() {
        (activity as MainActivity).finish()
    }
}