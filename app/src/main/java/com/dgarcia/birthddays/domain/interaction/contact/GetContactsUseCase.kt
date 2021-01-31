package com.dgarcia.birthddays.domain.interaction.contact

import com.dgarcia.birthddays.domain.base.BaseUseCase
import com.dgarcia.birthddays.domain.model.Contact
import com.dgarcia.birthddays.domain.model.Result

interface GetContactsUseCase: BaseUseCase<Any?, List<Contact>> {
    override suspend operator fun invoke(param: Any?): Result<List<Contact>>
}