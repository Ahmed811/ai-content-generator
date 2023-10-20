package com.virastudio.postgeneratorai.models

import com.khalilah.postgeneratorai.R
import com.virastudio.postgeneratorai.AppFeatureType


data class PostCardModel (val title:String,val image:Int,val appFeatureType: AppFeatureType)


val postCardList= listOf<PostCardModel>(
    PostCardModel("Face Posts Generator", R.drawable.facebook,AppFeatureType.FACEBOOK),
    PostCardModel("Twit Posts Generator", R.drawable.twitter,AppFeatureType.TWITTER),
    PostCardModel("Linkedin Posts Generator", R.drawable.linkedin,AppFeatureType.LINKEDIN),
    PostCardModel("Articles Posts Generator", R.drawable.article,AppFeatureType.ARTICLE),
    PostCardModel("Art Generator", R.drawable.art,AppFeatureType.ART),
    PostCardModel("Ask Ai GPT", R.drawable.ai,AppFeatureType.CHATGPT)
)