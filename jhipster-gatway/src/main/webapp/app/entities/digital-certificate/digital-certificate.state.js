(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('digital-certificate', {
            parent: 'entity',
            url: '/digital-certificate',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.digitalCertificate.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/digital-certificate/digital-certificates.html',
                    controller: 'DigitalCertificateController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('digitalCertificate');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('digital-certificate-detail', {
            parent: 'digital-certificate',
            url: '/digital-certificate/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.digitalCertificate.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/digital-certificate/digital-certificate-detail.html',
                    controller: 'DigitalCertificateDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('digitalCertificate');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DigitalCertificate', function($stateParams, DigitalCertificate) {
                    return DigitalCertificate.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'digital-certificate',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('digital-certificate-detail.edit', {
            parent: 'digital-certificate-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/digital-certificate/digital-certificate-dialog.html',
                    controller: 'DigitalCertificateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DigitalCertificate', function(DigitalCertificate) {
                            return DigitalCertificate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('digital-certificate.new', {
            parent: 'digital-certificate',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/digital-certificate/digital-certificate-dialog.html',
                    controller: 'DigitalCertificateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                certChain: null,
                                certificateSerial: null,
                                termStartDate: null,
                                termEndDate: null,
                                signature: null,
                                encryption: null,
                                activeYn: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('digital-certificate', null, { reload: 'digital-certificate' });
                }, function() {
                    $state.go('digital-certificate');
                });
            }]
        })
        .state('digital-certificate.edit', {
            parent: 'digital-certificate',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/digital-certificate/digital-certificate-dialog.html',
                    controller: 'DigitalCertificateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DigitalCertificate', function(DigitalCertificate) {
                            return DigitalCertificate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('digital-certificate', null, { reload: 'digital-certificate' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('digital-certificate.delete', {
            parent: 'digital-certificate',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/digital-certificate/digital-certificate-delete-dialog.html',
                    controller: 'DigitalCertificateDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DigitalCertificate', function(DigitalCertificate) {
                            return DigitalCertificate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('digital-certificate', null, { reload: 'digital-certificate' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
