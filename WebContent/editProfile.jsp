<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="theme.css" type="text/css"> </head>
  <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 

<body class="" style="background-image: url('https://motionarray.imgix.net/preview-86316-Fzhsm5Qo04-high_0014.jpg?w=750&amp;q=60&amp;fit=max&amp;auto=format');background-size:cover;" >
<%@ include file="header.jsp" %>
<c:if test = "${empty sessionScope.customer}">
<%
    String redirectURL = "login.jsp";
    response.sendRedirect(redirectURL);
%>
</c:if>
  <div class="py-4">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-light text-center display-3">Edit Profile</h1>
        </div>
      </div>
    </div>
  </div>
  <div class="">
    <div class="container">
      <div class="row">
        <div class="col-md-7 offset-md-3">
          <form class="" action="editProfile.do" method="post">
            <div class="form-group">
              <label class="text-light">Email address</label>
              <input type="email" class="form-control"  value="${sessionScope.customer.email}" name="email"> </div>
            <div class="form-group">
              <label class="text-light">Name</label>
              <input type="text" class="form-control"  value="${sessionScope.customer.customerName}" name="customerName"> </div>
            <div class="form-group">
              <label for="exampleInputEmail1" class="text-light">Address</label>
              <input type="text" class="form-control" id="inlineFormInput"  value="${sessionScope.customer.address}" name="address"> </div>
            <div class="form-group">
              <label for="exampleInputEmail1" class="text-light">Date of Birth</label>
              <input type="text" class="form-control" id="inlineFormInput" value="${sessionScope.customer.dateOfBirth}" name="dateOfBirth" onfocus="(this.type='date')"> </div>
            	<a href="updatePassword.jsp"><text class="text-light">Update Password</text></a>
            <div class="form-row text-center">
              <div class="col-12">
                <button type="submit" class="btn btn-dark">Submit</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <img src="https://pingendo.com/site-assets/Pingendo_logo_big.png" class="d-block" alt="Pingendo logo" height="16"> </div>
</body>

</html>