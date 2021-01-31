package com.dgarcia.birthddays.domain.base

import com.dgarcia.birthddays.domain.model.Result

interface BaseUseCase<T : Any?, R: Any> {
    suspend operator fun invoke(param: T): Result<R>
}