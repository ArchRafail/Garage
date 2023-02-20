package com.example.garage.part

class Part(name: String, description: String, picture: Int) {
    private var name: String
    private var description: String
    private var pictureResource: Int
    private var choose: Boolean

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getDescription(): String {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getPictureResource(): Int {
        return pictureResource
    }

    fun setPictureResource(pictureResource: Int) {
        this.pictureResource = pictureResource
    }

    fun getChoose(): Boolean {
        return choose
    }

    fun setChoose(choose: Boolean) {
        this.choose = choose
    }

    init {
        this.name = name
        this.description = description
        this.pictureResource = picture
        this.choose = false
    }
}