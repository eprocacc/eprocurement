'use strict';

describe('Controller Tests', function() {

    describe('State Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockState, MockDistrict, MockCountry;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockState = jasmine.createSpy('MockState');
            MockDistrict = jasmine.createSpy('MockDistrict');
            MockCountry = jasmine.createSpy('MockCountry');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'State': MockState,
                'District': MockDistrict,
                'Country': MockCountry
            };
            createController = function() {
                $injector.get('$controller')("StateDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'jhipstergatwayApp:stateUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
