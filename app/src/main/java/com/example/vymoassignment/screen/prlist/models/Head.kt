package com.example.vymoassignment.screen.prlist.models

data class Head(
    val label: String,
    val ref: String,
    val repo: RepoX,
    val sha: String,
    val user: UserX
)