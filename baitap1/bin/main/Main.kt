import java.util.Scanner
import kotlin.math.abs

class PhanSo(var tu: Int, var mau: Int) {

    init {
        if (mau == 0) {
            throw IllegalArgumentException("Mẫu số không được bằng 0")
        }
    }

    fun nhap(scanner: Scanner) {
        do {
            print("Nhập tử số: ")
            tu = scanner.nextInt()
            print("Nhập mẫu số: ")
            mau = scanner.nextInt()
            if (mau == 0) {
                println("Mẫu số không được bằng 0, nhập lại!")
            }
        } while (mau == 0)
    }

    fun xuat() {
        println("$tu/$mau")
    }

    fun toiGian() {
        val gcd = gcd(abs(tu), abs(mau))
        tu /= gcd
        mau /= gcd
        if (mau < 0) {
            tu = -tu
            mau = -mau
        }
    }

    fun soSanh(ps: PhanSo): Int {
        val a = this.tu * ps.mau
        val b = ps.tu * this.mau
        return when {
            a < b -> -1
            a > b -> 1
            else -> 0
        }
    }

    fun cong(ps: PhanSo): PhanSo {
        val tuMoi = this.tu * ps.mau + ps.tu * this.mau
        val mauMoi = this.mau * ps.mau
        val kq = PhanSo(tuMoi, mauMoi)
        kq.toiGian()
        return kq
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    print("Nhập số lượng phân số: ")
    val n = scanner.nextInt()
    val arr = Array(n) { PhanSo(1, 1) }

    for (i in arr.indices) {
        println("Nhập phân số thứ ${i + 1}:")
        arr[i] = PhanSo(1, 1)
        arr[i].nhap(scanner)
    }

    println("Các phân số vừa nhập:")
    arr.forEach { it.xuat() }

    println("Các phân số sau khi tối giản:")
    arr.forEach {
        it.toiGian()
        it.xuat()
    }

    var tong = arr[0]
    for (i in 1 until n) {
        tong = tong.cong(arr[i])
    }
    println("Tổng các phân số:")
    tong.xuat()

    var max = arr[0]
    for (i in 1 until n) {
        if (arr[i].soSanh(max) > 0) {
            max = arr[i]
        }
    }
    print("Phân số lớn nhất: ")
    max.xuat()

    arr.sortWith { a, b -> b.soSanh(a) }
    println("Mảng sau khi sắp xếp giảm dần:")
    arr.forEach { it.xuat() }
}
