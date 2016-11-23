package com.clevercells

import com.clevercells.benchmarks.Benchmarker

object Launcher extends App {
//  val message: String = "Unicode规范中推荐的标记字节顺序的方法是BOM。中国问题论坛BOM不是“Bill Of Material”的BOM表，而是Byte Order Mark。"
//  val message: String = "大烧鴇卖"
//  val message: String = "鴇"
  val message: String = "大烧卖"
//  val message: String = "ffuckyou mother"

  Benchmarker.benchmark("markwords.txt", message)
}