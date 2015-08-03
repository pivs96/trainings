angular.module('frontendApp').factory('userService', function ($resource, $q) {
  var userId = 0;
  var getUserId  = function(){
    return userId;
  };
  var $userResource = $resource('http://localhost:8080/user?id=:id', {id: '@id'}, {
    getMentoringTrainings: {
      url: 'http://localhost:8080/user/mentoringTrainings?userId=:id',
      params:{id:getUserId},
      method: 'GET',
      isArray: true
    },
    getVisitingTrainings: {
      url: 'http://localhost:8080/user/visitingTrainings?userId=:id',
      params:{id:getUserId},
      method: 'GET',
      isArray: true
    }
  });
  return {
    userResource: $userResource,
    getUserProfileData: function () {
      return $userResource.get({id: userId}).$promise;
    },

    setUserId: function (_userId) {
      userId = _userId;
    }
  }
});


