const licenseAPI = {
  getInlicenseList (search) {
    return axios
            .get('/license/license', {
              params: { search }
            })
            .then(res => res.data)
  },

  addLicense (license) {
    this.cleanFields(license)
    return axios.post('/license/license', license).then(res => res)
  },

  updateLicense (license) {
    this.cleanFields(license)
    return axios.put('/license/license', license).then(res => res)
  },

  getOutlicenseList (search) {
    return axios
            .get('/license/license', {
              params: { search }
            })
            .then(res => res.data)
  },

  deleteLicense (ids) {
    return axios.delete('/license/license/' + ids).then(res => res)
  },

  getfileList1 () {
    return axios.get(`/license/filelist1`).then(res => res.data)
  },

  getfileList2 () {
    return axios.get(`/license/filelist2`).then(res => res.data)
  },
  getLicenseGoods (licenseid) {
    return axios
            .get('/license/license/goods', {
              params: {
                licenseid
              }
            })
            .then(res => res.data)
  },
  addLicenseGoods (licensegoods) {
    return axios.post('/license/license/goods', licensegoods).then(res => res.data)
  },
  updateLicenseGoods (licensegoods) {
    return axios({
      method: 'put',
      url: '/license/license/goods/' + licensegoods.id,
      data: licensegoods
    }).then(res => res.data)
  },
  deleteLicenseGoods (ids) {
    let strIds = ids.join(',')
    return axios.delete('/license/license/goods/' + strIds).then(res => res.data)
  },
  cleanFields (obj) {
    let arr = ['skuName', 'memo', 'id', 'sku', 'licenseKey', 'count', 'companyName', 'type', 'expirationDateOfLicense']
    for (let prop in obj) {
      if (!arr.includes(prop)) {
        delete obj[prop]
      }
    }
  }
}

export default licenseAPI
