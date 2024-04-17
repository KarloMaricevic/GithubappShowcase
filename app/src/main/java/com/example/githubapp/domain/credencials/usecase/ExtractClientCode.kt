package com.example.githubapp.domain.credencials.usecase

import javax.inject.Inject

class ExtractClientCode @Inject constructor() {

    private companion object {
        const val CLIENT_CODE_PARAM_NAME = "code"
        const val PARAM_VALUE_DELIMITER = "="
        const val ENTRY_DELIMITER = "&"
    }


    operator fun invoke(url: String): String {
        val indexOfClientIdParam = url.indexOf(CLIENT_CODE_PARAM_NAME)
        val indexOfClientIdValue =
            indexOfClientIdParam + CLIENT_CODE_PARAM_NAME.length + PARAM_VALUE_DELIMITER.length
        val indexOfEntryDelimiterAfterClientId = url.indexOf(ENTRY_DELIMITER, indexOfClientIdValue)
        return url.substring(indexOfClientIdValue, indexOfEntryDelimiterAfterClientId)
    }
}
