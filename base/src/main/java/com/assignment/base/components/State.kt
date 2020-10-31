package com.assignment.base.components

/**
 * View State Enum
 */
enum class State {
    /**
     * View Is IDLE
     */
    IDLE,
    /**
     * View is Loading something so Show Loading View
     */
    LOADING,
    /**
     * There is some error on View
     */
    ERROR,
    /**
     * In case of no internet
     */

    OFFLINE
}