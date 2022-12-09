const product = document.querySelector("#product");
const total = document.querySelector("#total");
const productData = JSON.parse(localStorage.getItem("productData"));
const storeId = JSON.parse(localStorage.getItem("productStore"));
const localCar = localStorage.getItem("car"); //取得car物件
const cart = document.querySelector("#cart");
const confirmMeal = document.querySelector(".confirm-meal");
const baseUrl = "http://localhost:8080/FasteroV2/OrderMaster";
const userId = 2;
const amount = productData?.map((e) => e.price).reduce((p, v) => p + v);

confirmMeal.onclick = () => {
  const remark = document.querySelector("#remark").value;
  let product = productData?.filter((e) => e.price !== 0);
  console.log(product);
  if (!product?.length) return alert("您的購物車沒有東西喔～");

  // return;
  fetch(baseUrl, {
    method: "POST",
    headers: {
      "Content-type": "application/json;charset=utf-8",
    },
    body: JSON.stringify({
      userId,
      storeId,
      remark,
      amount,
      product,
    }),
  })
    .then((res) => res.json())
    .then((data) => {
      if (data) {
        alert("新增成功！");
        localStorage.clear();
        window.location.href = "/Index/index.html";
        return;
      }

      alert("新增失敗");
    });
};

function Template(name, quantity, unitPrice) {
  return `<tr>
    <td>${name}</td>
    <td class="price">${unitPrice / quantity}</td>
    <td>${quantity}</td>
    <td class="total_price">${unitPrice}</td>
  </tr>`;
}

function init() {
  if (!productData) return;
  addCart();
  product.innerHTML = productData
    .filter((e) => e.price !== 0)
    .map((e) => Template(e.name, e.quantity, e.price))
    .join(""); //.toString().replaceAll(',','')
  total.textContent = amount;
}
const addCart = () => {
  cart.innerHTML = !localCar
    ? ""
    : `<span class=" cart-count">${Number(localCar)}</span>`;
};

window.addEventListener("load", init);
