package com.wenger.natifetask3.utils

import com.wenger.natifetask3.data.ResultResponse
import com.wenger.natifetask3.data.User

fun ResultResponse.toUser() = User(
    uuid = id.uuid,
    name = name.firstName,
    lastName = name.lastName,
    userPhoto = picture.url
)