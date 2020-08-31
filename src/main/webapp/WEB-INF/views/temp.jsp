<c:forEach items="${adverts}" var="advert">
    <br><br>
    <p>--------------------------------------------</p>
    <h4>${advert.category.name}</h4>
    <h1><a href='<c:url value="/adds/${advert.id}" />'>${advert.title}</a></h1>
    <h3>Cena: ${advert.price}PLN</h3>
    <p>Utworzono: ${advert.created.toString()}</p>
    <p>--------------------------------------------</p>
    <br><br>
</c:forEach>