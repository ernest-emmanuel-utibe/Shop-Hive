This is a web application that allows customers place order from their fav restaurant

-create cart table: a customer can add meal to cart, when done can proceed to view all items in the cart

what a service provider can do:

create account (working)
login (working)
add meal (working)
update profile (working)
delete a meal (working)
edit a meal (working)
add order to cart (not working)
view all orders (not done yet)
get all customers info that has ordered from me
verify service provider
send push notifications to all my customers
what a customer can do on the platform:

create account (working)
login (working)
update profile (working)
find all the service providers in a city (working)
place order (working)
find customer by date (working)
add order to cart (not working)
view all orders (not done yet)
verify customer
pay with paystack
what a staff can do on the platform

registration

login

list all customers on the platform

update profile

list all service providers on the platform

to do

service provider can be in diff cities
add a type of food created by service provider
add a order status
create a cart class, add order to cart
no customer_order and order mapping in db
for the update api, use path param for the email
LocalDateTime localDateTime = "12/23/2021" localDateTime = LocalDateTime.parse(dateTime.toUpperCase()), DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));

.getDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a"));

private LocalDate today;

LocalDateTime localDateTime = DateTimeConverter.convertStringToDateTime(dateTime, today);