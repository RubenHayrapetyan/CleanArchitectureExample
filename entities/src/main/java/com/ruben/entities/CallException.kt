package com.ruben.entities

data class CallException<ErrorBody>(val errorCode: Int, val errorMessage:String?=null, val errorBody: ErrorBody? = null)