package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.BaseResp;

/**
 * 作者：sunnyzeng on 2017/12/19 17:22
 * 描述：
 * {
 * "data": {
 * "base64Image": "iVBORw0KGgoAAAANSUhEUgAAADwAAAAUCAIAAABeYcl+AAABrklEQVR42p1X200EMQx0GxSAdH+IHyQauDYoAgqgFST+rxDagD6IFMln5uE1J1mr3ewmHo/HTja+f77mdvn4XFYf4W39bG48XbrOa0xQTlw2/prwHFZeuYKJQ391ibwCqc0ijj/HsUwXzAoeWna+Py1z2bkNd5N0+KZJ6ZVpeLERL3PK2/b0/ursUNwO7lCQwcElbliUI07o1XGOTApAUiuhVyQhJ4M8nNQYNIyw8HqFDGMIKYCUh6vFHARegek+UQ0pvb5DrgiadqAlROb+8e5t2XrcN/veEfzy8LxNpvcqD05fysPlCBQMVjElUMAtyU6sG7frvCGVKlteX4gwUvObiGsYnMPkOG20uQBo7r4sL9krYBBQ1scKPYGCL67akKFUTfe12IDOWVXH/AhM9yX7R9NuR3S1mAtVMXAt3sB0qoIDSAupAbcjOnnUucx9RZn3sk5Y0xJ3cJvMbZxxM51uG2fQtW80/Vsihi/D9cLJCUY65qWciHvXshfjjtjvW/2gA+REfHhAl7jF2YMPuC7o5teD43egJ2c6d7QKN9Odlf/7HwVqdulqhMen7V9GpHq2IkM55QAAAABJRU5ErkJggg==",
 * "imageCodeId": "6466f112d0044e1f9c615fea4240447f"
 * },
 * "message": "成功",
 * "status": 200
 * }
 */

public class ImageResp extends BaseResp<ImageResp> {
    private String base64Image;
    private String imageCodeId;

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getImageCodeId() {
        return imageCodeId;
    }

    public void setImageCodeId(String imageCodeId) {
        this.imageCodeId = imageCodeId;
    }
}
