package com.sirius.test_app.presentation.mapper

import com.sirius.test_app.data.DataModel
import com.sirius.test_app.presentation.model.recycler.*
import com.sirius.test_app.recycler.item.RecyclerItem

/**
 * Примечание для проверяющих
 * Лучше закрыть это дело интерфейсом и сделать его общим для нескольких экранов
 * Плюсом тестировать будет удобнее
 * Но в данной задаче бессмысленно
 */
class MainPresentationMapper {

    fun map(dataModel: DataModel): List<RecyclerItem> {
        return listOf(
            dataModel.mapToHeader(),
            dataModel.mapToDescription(),
            dataModel.mapToRating()
        ) + dataModel.mapToReviews() + MainItemFooter()
    }

    private fun DataModel.mapToHeader(): MainItemHeader {
        return MainItemHeader(
            title = name,
            rating = rating,
            reviewsCount = gradeCnt,
            logoLink = logo,
            imageLink = image
        )
    }

    private fun DataModel.mapToDescription(): MainItemDescription {
        return MainItemDescription(
            tags = tags,
            description = description
        )
    }

    private fun DataModel.mapToRating(): MainItemRating {
        return MainItemRating(
            rating = rating,
            reviewsCount = gradeCnt
        )
    }

    private fun DataModel.mapToReviews(): List<MainItemReview> {
        return reviews.map {
            MainItemReview(
                avatarLink = it.userImage,
                name = it.userName,
                date = it.date,
                review = it.message
            )
        }
    }
}
