package com.shinjaehun.room

class MemberAlreadyExistsException: Exception(
    "There is already a member with that username in the room"
)