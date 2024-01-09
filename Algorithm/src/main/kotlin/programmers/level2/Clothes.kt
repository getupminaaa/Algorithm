fun main() {
    val solution = Clothes()
    /*val clothes: Array<Array<String>> = arrayOf(
        arrayOf("crow_mask", "face"), arrayOf("blue_sunglasses", "face"),
        arrayOf("smoky_makeup", "face")
    )*/
    val clothes: Array<Array<String>> = arrayOf(
        arrayOf("yellow_hat", "headgear"), arrayOf("blue_sunglasses", "eyewear"),
        arrayOf("green_turban", "headgear")
    )
    //return = [2,1]
    solution.solution(clothes)
}

class Clothes {
    fun solution(clothes: Array<Array<String>>): Int {
        var answer = 0
        answer = clothes.groupingBy{it[1]}
            //옷의 종류로 묶어서 새로운 그룹으로 만듦
            .eachCount()
            //옷의 종류별로 개수를 세어줌
            .map{it.value+1}
            //개수 +1을 한뒤 새로운 리스트로 만듦
            .reduce{acc,i -> acc.times(i)}
            //서로 곱해줌
            .minus(1)
             //-1을 해줌(아무것도 안입었을 경우는 없으니까 -1)
        return answer
    }
}
