package com.alican.predecessorcompanion.ui.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.data.remote.api.WebService
import com.alican.predecessorcompanion.utils.ResultWrapper
import com.alican.predecessorcompanion.utils.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HeroesViewModel @Inject constructor(
) : ViewModel(){

}