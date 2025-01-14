package com.example.cleanarchitectureformvalidation.domain.use_case

class ValidateTerms {
    fun execute(acceptedTerms: Boolean): ValidationResult {
        if (!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please accept terms"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}