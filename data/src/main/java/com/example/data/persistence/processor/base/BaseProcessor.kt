package com.example.data.persistence.processor.base

import com.example.data.persistence.dao.RepoDao

abstract class BaseProcessor<T>(private val dao: RepoDao) {

}