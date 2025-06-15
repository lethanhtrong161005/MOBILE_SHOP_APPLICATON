function searchMobile() {
    var keyword = document.getElementById("searchInput").value.toLowerCase();
    var rows = document.querySelectorAll("#mobileTable tbody tr");
    rows.forEach(function (row) {
        var id = row.cells[0].textContent.toLowerCase();
        var name = row.cells[1].textContent.toLowerCase();
        row.style.display = (id.includes(keyword) || name.includes(keyword)) ? "" : "none";
    });
}

function deleteRow(btn) {
    if (confirm("Are you sure to delete this mobile?")) {
        var row = btn.parentNode.parentNode;
        row.parentNode.removeChild(row);
    }
}

function updateRow(btn) {
    alert("Update logic to DB goes here (not implemented)");
}

function insertMobile(e) {
    e.preventDefault();
    var id = document.getElementById("newId").value;
    var name = document.getElementById("newName").value;
    var price = document.getElementById("newPrice").value;
    var desc = document.getElementById("newDesc").value;
    var qty = document.getElementById("newQty").value;
    var notSale = document.getElementById("newNotSale").value;

    var table = document.getElementById("mobileTable").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow();

    newRow.innerHTML = `
        <td>${id}</td>
        <td>${name}</td>
        <td contenteditable="true">${price}</td>
        <td contenteditable="true">${desc}</td>
        <td contenteditable="true">${qty}</td>
        <td contenteditable="true">${notSale}</td>
        <td>
            <button class="btn btn-danger btn-sm" onclick="deleteRow(this)">Delete</button>
            <button class="btn btn-success btn-sm" onclick="updateRow(this)">Update</button>
        </td>
    `;

    // Reset form
    e.target.reset();
}

let cart = [];

function searchDevices() {
    const min = parseFloat(document.getElementById('minPrice').value);
    const max = parseFloat(document.getElementById('maxPrice').value);

    // Fake result
    const results = [{
            id: 'M01',
            name: 'iPhone 15',
            price: 1200
        },
        {
            id: 'M02',
            name: 'Samsung S23',
            price: 999
        },
        {
            id: 'M03',
            name: 'Xiaomi 13',
            price: 650
        }
    ].filter(d => d.price >= min && d.price <= max);

    const listDiv = document.getElementById('device-list');
    listDiv.innerHTML = results.map(d => `
                <div class="panel panel-default">
                    <div class="panel-body">
                        <strong>${d.name}</strong> - $${d.price}
                        <button class="btn btn-success btn-sm pull-right" onclick='addToCart(${JSON.stringify(d)})'>Add to Cart</button>
                    </div>
                </div>
            `).join('');
}

function addToCart(device) {
    const existing = cart.find(item => item.id === device.id);
    if (existing) {
        existing.quantity++;
    } else {
        device.quantity = 1;
        cart.push(device);
    }
    updateCartCount();
    localStorage.setItem('cart', JSON.stringify(cart));
}

function updateCartCount() {
    document.getElementById('cart-count').innerText = cart.reduce((sum, item) => sum + item.quantity, 0);
}

window.onload = function () {
    const savedCart = JSON.parse(localStorage.getItem('cart')) || [];
    cart = savedCart;
    updateCartCount();
}

setTimeout(() => {
        const alert = document.querySelector('#autoDismissAlert');
        if (alert) {
            alert.style.transition = 'opacity 0.5s ease';
            alert.style.opacity = 0;
            setTimeout(() => alert.remove(), 500);
        }
}, 2000);





function showConfirmForm(event) {
  event.preventDefault(); // Chặn submit mặc định

  // Lấy các input
  var id = document.getElementById('newId');
  var name = document.getElementById('newName');
  var year = document.getElementById('newYear');
  var price = document.getElementById('newPrice');
  var desc = document.getElementById('newDesc');
  var qty = document.getElementById('newQty');
  var status = document.getElementById('newNotSale');

  // Xoá lỗi cũ nếu có
  var errors = document.querySelectorAll('.error-message');
  errors.forEach(function(el) { el.remove(); });

  // Luôn hiển thị modal xác nhận (bỏ qua check hợp lệ)
  var confirmContent = document.getElementById("confirmContent");
  confirmContent.innerHTML =
    "<strong>ID:</strong> " + id.value + "<br>" +
    "<strong>Name:</strong> " + name.value + "<br>" +
    "<strong>Year:</strong> " + year.value + "<br>" +
    "<strong>Price:</strong> " + price.value + "<br>" +
    "<strong>Description:</strong> " + (desc.value || "(none)") + "<br>" +
    "<strong>Quantity:</strong> " + (qty.value || "0") + "<br>" +
    "<strong>Status:</strong> " + (status.value === "true" ? "Not For Sale" : "For Sale");

  document.getElementById("confirmModal").style.display = "flex";
}

function closeConfirm() {
  document.getElementById("confirmModal").style.display = "none";
}

function submitConfirmed() {
  document.getElementById("insertForm").submit();
}
