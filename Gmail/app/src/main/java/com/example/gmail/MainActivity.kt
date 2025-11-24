package com.example.gmail

import Email
import EmailAdapter
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emails = ArrayList<Email>()
        emails.add(Email(R.drawable.ic_email_i, "Instagram", "huongg_maii22 ơi, hãy bắt kịp các khoảnh khắc...", "Chủ Nhật",
            "huongg_maii22 ơi, hãy bắt kịp các khoảnh khắc chỉ dành cho bạn. justtdung_, imyour2902spring và những người khác đã đăng nội dung mới. Bắt kịp trên Instagram"))

        emails.add(Email(R.drawable.ic_email_h, "Hay Day", "We're thankful for you! Enjoy your free Thanks...", "Thứ Bảy",
            "We're thankful for you! Enjoy your free Thanksgiving gift. Privacy Policy|Parent|..."))

        emails.add(Email(R.drawable.ic_email_c, "Cloudinary Team", "Try our low-code option for Cloudinary", "Thứ Bảy",
            "Hi Mai, If you often write scripts to tag, approve, transform, or sync media, there's a faster way...")) // Điều chỉnh tên người nhận thành "Mai"

        emails.add(Email(R.drawable.ic_email_l, "LeetCode", "LIMITED TIME OFFER - $40 off Annual Sub...", "Thứ Sáu",
            "Get $40 off on LeetCode Annual Premium Subscription. Unlock LeetCode Premium for ju..."))

        emails.add(Email(R.drawable.ic_email_t, "Techcombank", "[QC] - Quẹt thẻ săn Black Friday, hoàn về tiền...", "Thứ Sáu",
            "Sẵn sàng cho ngày hội Black Friday lớn nhất năm, thẻ thanh toán trên tay, quẹt ngay deal h..."))

        emails.add(Email(R.drawable.ic_email_g, "Google One", "Mẹo hay của Google One về bộ nhớ", "Thứ Sáu",
            "Giải phóng bộ nhớ, quản lý dung lượng lưu trữ và nhiều hoạt động khác"))

        emails.add(Email(R.drawable.ic_email_i, "Instagram", "huongg_maii22 ơi, hãy xem pqc.mingxi, hu...", "Thứ Năm",
            "huongg_maii22 ơi, hãy xem thuan231, hu.le.29 và những người khác đã đăng nội dung mới. Bắt kịp trên Instagram"))
        emails.add(Email(R.drawable.ic_email_h,"Hay Day", "Cơ hội cuối cùng: Nhận gói kim cương miễn phí!", "3:30 PM", "Chào Mai Thanh Hương, thời gian nhận gói kim cương miễn phí trong sự kiện thu hoạch đã sắp hết. Đăng nhập ngay để không bỏ lỡ phần quà giá trị!"))
        val listView: ListView = findViewById(R.id.listView)
        val emailAdapter = EmailAdapter(this, emails)
        listView.adapter = emailAdapter
    }
}
