'use strict';

describe('Controller Tests', function() {

    describe('DigitalCertificate Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDigitalCertificate, MockSupplier;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDigitalCertificate = jasmine.createSpy('MockDigitalCertificate');
            MockSupplier = jasmine.createSpy('MockSupplier');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'DigitalCertificate': MockDigitalCertificate,
                'Supplier': MockSupplier
            };
            createController = function() {
                $injector.get('$controller')("DigitalCertificateDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'jhipstergatwayApp:digitalCertificateUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
