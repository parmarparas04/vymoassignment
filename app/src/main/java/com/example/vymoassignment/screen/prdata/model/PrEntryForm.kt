package  com.example.vymoassignment.screen.prdata.model

import android.text.TextUtils
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.vymoassignment.BR
import com.example.vymoassignment.R

class PrEntryForm : BaseObservable() {
    private val activate = PrEntryFields()
    private val errors = PrEntryError()

    @get:Bindable
    var name: String?
        get() = activate.name
        set(name) {
            activate.name = name
            notifyPropertyChanged(BR.name)
        }
    @get:Bindable
    val nameError: Int?
        get() = errors.name

    private fun setNameError(str: Int) {
        errors.name = str
        notifyPropertyChanged(BR.nameError)
    }

    private fun clearNameError() {
        errors.name = null
        notifyPropertyChanged(BR.nameError)
    }


    @get:Bindable
    var repo: String?
        get() = activate.repo
        set(name) {
            activate.repo = name
            notifyPropertyChanged(BR.repo)
        }
    @get:Bindable
    val repoError: Int?
        get() = errors.repo

    private fun setRepoError(str: Int) {
        errors.repo = str
        notifyPropertyChanged(BR.repoError)
    }

    private fun clearRepoError() {
        errors.name = null
        notifyPropertyChanged(BR.repoError)
    }
    /**
     * form validation for phone number
     */
    fun validateFields(): Boolean {
        if (TextUtils.isEmpty(name)) {
            setNameError(R.string.nameInputError)
            return false
        } else {
            clearNameError()
        }

        if (TextUtils.isEmpty(repo)) {
            setRepoError(R.string.repoInputError)
            return false
        } else {
            clearRepoError()
        }
        return true
    }


}