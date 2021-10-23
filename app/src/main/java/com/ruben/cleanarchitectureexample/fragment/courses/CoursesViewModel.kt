package com.ruben.cleanarchitectureexample.fragment.courses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ruben.domain.interactors.CoursesListInteractor
import com.ruben.entities.Result
import com.ruben.entities.localmodels.courses.CoursesItemLocal
import com.ruben.cleanarchitectureexample.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoursesViewModel(
    private val coursesListInteractor: CoursesListInteractor
) : BaseViewModel() {

    private val _getCoursesList by lazy { MutableLiveData<List<CoursesItemLocal>>() }
    val getCoursesItem: LiveData<List<CoursesItemLocal>> get() = _getCoursesList

    private val _loadingData by lazy { MutableLiveData<Boolean>() }
    val loadingData: LiveData<Boolean> get() = _loadingData

    private val _errorNullData by lazy { MutableLiveData<String>() }
    val errorNullData get() = _errorNullData

    private val _notMoreItem by lazy { MutableLiveData<Unit>() }
    val notMoreItem get() = _notMoreItem

    private val _dbDataIsEmpty by lazy { MutableLiveData<Boolean>() }
    val dbDataIsEmpty get() = _dbDataIsEmpty

    init {
        viewModelScope.launch {
            getAllCourses()
        }
    }

    fun loadDataDb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coursesListInteractor.getCoursesListFromDb()
            }
        }
    }

    fun getAllCourses() {
        _loadingData.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = coursesListInteractor.getCoursesListResponse()) {
                    is Result.Success -> {
                        _getCoursesList.value = result.data
                    }
                    is Result.Error -> {
                        _errorNullData.value = result.errors.errorMessage
                    }
                }
            }
        }
        _loadingData.value = false
    }
}