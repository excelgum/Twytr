package com.tipro.tipro
class UserDto {
    var name: String = ""
    var comment: String = ""

    constructor()

    constructor(name: String, comment: String) {
        this.name = name
        this.comment = comment
    }
}