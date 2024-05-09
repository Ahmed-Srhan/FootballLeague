package com.srhan.footballleague.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srhan.footballleague.data.model.Resource
import com.srhan.footballleague.domain.model.CompetitionDetailsModel
import com.srhan.footballleague.domain.usecase.CacheCompetitionUseCade
import com.srhan.footballleague.domain.usecase.GetCompetitionFromLocalUseCase
import com.srhan.footballleague.domain.usecase.GetCompetitionFromNetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompetitionViewModel @Inject constructor(
    private val getCompetitionFromNetworkUseCase: GetCompetitionFromNetworkUseCase,
    private val cacheCompetitionUseCase: CacheCompetitionUseCade,
    private val getCompetitionsFromCache: GetCompetitionFromLocalUseCase
) : ViewModel() {
    private val _competitionsNetworkState: MutableStateFlow<Resource<List<CompetitionDetailsModel>>> =
        MutableStateFlow(Resource.Loading())
    val competitionsNetworkState = _competitionsNetworkState.asStateFlow()
    private val _competitionsLocalState: MutableStateFlow<Resource<List<CompetitionDetailsModel>>> =
        MutableStateFlow(Resource.Loading())
    val competitionsLocalState = _competitionsLocalState.asStateFlow()

    init {
        getCompetitionsFromNetwork()

    }


    private fun getCompetitionsFromNetwork() = viewModelScope.launch {
        getCompetitionFromNetworkUseCase.invoke().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _competitionsNetworkState.emit(Resource.Loading())
                }

                is Resource.Success -> {
                    result.data?.competitionModel?.let {
                        _competitionsNetworkState.emit(Resource.Success(it))
                        cacheCompetitionUseCase.invoke(it)
                    }

                }

                is Resource.Error -> {
                    result.data?.competitionModel?.let {
                        _competitionsNetworkState.emit(Resource.Success(it))
                        cacheCompetitionUseCase.invoke(it)
                    }
                }
            }


        }
    }

    fun getCompetitionsFromLocal() = viewModelScope.launch(IO) {
        getCompetitionsFromCache.invoke().collect {
            _competitionsLocalState.emit(Resource.Success(it))
        }

    }

    override fun onCleared() {
        super.onCleared()
        _competitionsNetworkState.value = Resource.Loading()
    }


}