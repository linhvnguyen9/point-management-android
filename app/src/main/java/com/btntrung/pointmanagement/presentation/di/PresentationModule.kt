package com.btntrung.pointmanagement.presentation.di

import com.btntrung.pointmanagement.presentation.manager.ManagerMainViewModel
import com.btntrung.pointmanagement.presentation.manager.pointinput.PointInputViewModel
import com.btntrung.pointmanagement.presentation.manager.studentlist.StudentListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ManagerMainViewModel(get(), get()) }
    viewModel { StudentListViewModel(get()) }
    viewModel { PointInputViewModel(get(), get()) }
}