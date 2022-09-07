package com.wenger.natifetask3.domain

import com.wenger.natifetask3.data.ResultResponse

interface UserRepository {

    fun getAllUsers(list: (List<ResultResponse>) -> Unit) : (List<ResultResponse>) -> Unit
}