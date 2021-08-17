let msgArr = [
  '환율: @{exchangePrice} @{country}/USD', //### exchangePrice
  '수취금액은 @{remittancePrice} @{country} 입니다.' //### remittancePrice
];
let originCountyExchange = 0;
let countyExchange = 0;
const SUCCESS_CODE = 200;
const FAIL_CODE    = 100;


let index = {
    init : function () {
        $('#resultText').text('');
        this.apiCall();
    },
    apiCall : function () {
        $.ajax({
            url: '/ajax/getApiCall',
            dataType: 'json',
            success: function (json) {
                if (json.resultCode == SUCCESS_CODE) {
                    let data = json.resultData;
                    let countryValue = $('#countryCombo').val();

                    if (countryValue == 'KRW') {
                        originCountyExchange = data.usdKrw.toFixed(2);
                        countyExchange = index.comma(data.usdKrw.toFixed(2));
                    } else if (countryValue == 'JPY') {
                        originCountyExchange = data.usdJpy.toFixed(2);
                        countyExchange = index.comma(data.usdJpy.toFixed(2));
                    } else {
                        originCountyExchange = data.usdPhp.toFixed(2);
                        countyExchange = index.comma(data.usdPhp.toFixed(2));;
                    }

                    $('#exchangePrice').text(
                        msgArr[0]
                        .replace(/@{exchangePrice}/gi, countyExchange)
                        .replace(/@{country}/gi, countryValue)
                    );
                }
            },
            error: function () {
                alert('통신오류 관리자에게 문의하세요.');
            }
        });
    },
    comma : function (num){
        let len, point, str;
        num = num + "";
        point = num.length % 3 ;
        len = num.length;
        str = num.substring(0, point);

        while (point < len) {
            if (str != "" && num.substring(point, point + 3).indexOf(".") < 0)
                str += ",";
            str += num.substring(point, point + 3);
            point += 3;
        }

        return str;
    },
    priceCheck: function () {
        let result = true;
        let price = $('#remittancePrice');
        if(price.val() == '' || price.val() == undefined || price.val() == null || price.val() <= 0 || price.val() > 10000) {
            $('#resultText').text('송금액이 바르지 않습니다');
            result = false;
        }

        return result;
    },
    calculate : function () {
        if (index.priceCheck()) {
            return originCountyExchange * $('#remittancePrice').val();
        }
    },
    submit: function () {
        index.init();
        if (index.calculate()) {
            let remittancePrice = index.comma(index.calculate().toFixed(2));
            $('#resultText').text(
                msgArr[1]
                .replace(/@{remittancePrice}/gi, remittancePrice)
                .replace(/@{country}/gi, $('#countryCombo').val())
            );
        }
    }

};

