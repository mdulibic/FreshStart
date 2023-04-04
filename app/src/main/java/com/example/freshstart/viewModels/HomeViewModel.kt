package com.example.freshstart.viewModels

import com.example.freshstart.navigation.IAppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appNavigator: IAppNavigator
): BaseViewModel() {

}