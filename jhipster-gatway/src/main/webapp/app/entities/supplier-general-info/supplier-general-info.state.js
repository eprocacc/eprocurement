(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('supplier-general-info', {
            parent: 'entity',
            url: '/supplier-general-info?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.supplierGeneralInfo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/supplier-general-info/supplier-general-infos.html',
                    controller: 'SupplierGeneralInfoController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('supplierGeneralInfo');
                    $translatePartialLoader.addPart('');
                    $translatePartialLoader.addPart('');
                    $translatePartialLoader.addPart('');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('supplier-general-info-detail', {
            parent: 'supplier-general-info',
            url: '/supplier-general-info/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.supplierGeneralInfo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/supplier-general-info/supplier-general-info-detail.html',
                    controller: 'SupplierGeneralInfoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('supplierGeneralInfo');
                    $translatePartialLoader.addPart('');
                    $translatePartialLoader.addPart('');
                    $translatePartialLoader.addPart('');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'SupplierGeneralInfo', function($stateParams, SupplierGeneralInfo) {
                    return SupplierGeneralInfo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'supplier-general-info',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('supplier-general-info-detail.edit', {
            parent: 'supplier-general-info-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-general-info/supplier-general-info-dialog.html',
                    controller: 'SupplierGeneralInfoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SupplierGeneralInfo', function(SupplierGeneralInfo) {
                            return SupplierGeneralInfo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('supplier-general-info.new', {
            parent: 'supplier-general-info',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-general-info/supplier-general-info-dialog.html',
                    controller: 'SupplierGeneralInfoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                firstName: null,
                                middleName: null,
                                lastName: null,
                                supplierStatus: null,
                                primaryEmail: null,
                                secondaryEmail: null,
                                website: null,
                                expiryDate: null,
                                applicationRefNo: null,
                                typeGoodsYn: null,
                                typeServicesYn: null,
                                typeWorksYn: null,
                                userType: null,
                                renewedDate: null,
                                regValidity: null,
                                submissionDate: null,
                                renewalNo: null,
                                approvaldate: null,
                                registrationBilledYn: null,
                                renewalBilledYn: null,
                                comments: null,
                                transactionRemarks: null,
                                blacklistedYn: null,
                                regMailNotificationYn: null,
                                applicationVerYn: null,
                                approverDecision: null,
                                sendPublishedMail: null,
                                certificateCheckYn: null,
                                mobileVerified: null,
                                smsEnabled: null,
                                emailVerified: null,
                                emailOTP: null,
                                emailOTPDate: null,
                                emailOTPExpired: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('supplier-general-info', null, { reload: 'supplier-general-info' });
                }, function() {
                    $state.go('supplier-general-info');
                });
            }]
        })
        .state('supplier-general-info.edit', {
            parent: 'supplier-general-info',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-general-info/supplier-general-info-dialog.html',
                    controller: 'SupplierGeneralInfoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SupplierGeneralInfo', function(SupplierGeneralInfo) {
                            return SupplierGeneralInfo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('supplier-general-info', null, { reload: 'supplier-general-info' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('supplier-general-info.delete', {
            parent: 'supplier-general-info',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-general-info/supplier-general-info-delete-dialog.html',
                    controller: 'SupplierGeneralInfoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['SupplierGeneralInfo', function(SupplierGeneralInfo) {
                            return SupplierGeneralInfo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('supplier-general-info', null, { reload: 'supplier-general-info' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
