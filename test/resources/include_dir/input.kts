// Let's resolve includes by directory!

//INCLUDE_DIR comment
//INCLUDE_DIR ./comment_dot

@file:IncludeDir("annotation")
@file:IncludeDir("./annotation_dot")

comment_include_1()
comment_include_2()
comment_include_3()
comment_dot_include_1()
comment_dot_include_2()
comment_dot_include_3()

annotation_include_1()
annotation_include_2()
annotation_include_3()
annotation_dot_include_1()
annotation_dot_include_2()
annotation_dot_include_3()

println("wow, so many includes")
