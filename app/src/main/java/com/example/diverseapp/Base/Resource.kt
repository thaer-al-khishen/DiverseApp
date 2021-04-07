package com.example.diverseapp.Base

//Wrapper class that contains the data and an error message to display if an error exists
data class Resource<T>(val throwable: Throwable?, val data: T?)